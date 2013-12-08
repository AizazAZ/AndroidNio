package com.example.androidnio;

import java.util.ArrayList;

import com.koushikdutta.async.AsyncServer;
import com.koushikdutta.async.AsyncServerSocket;
import com.koushikdutta.async.AsyncSocket;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.callback.ListenCallback;

public class CShareServer {
	ArrayList<AsyncServerSocket> mListeners = new ArrayList<AsyncServerSocket>();
	ArrayList<CShareRequest> mClients = new ArrayList<CShareRequest>();

	public void stop() {
		if (mListeners != null) {
			for (AsyncServerSocket listener : mListeners) {
				listener.stop();
			}
		}
	}

	ListenCallback mListenCallback = new ListenCallback() {

		@Override
		public void onCompleted(Exception ex) {
			report(ex);
		}

		@Override
		public void onAccepted(AsyncSocket socket) {

			System.out.println("用户来啦！");
			CShareRequest request = new CShareRequest(socket);
			mClients.add(request);
		}

		@Override
		public void onListening(AsyncServerSocket socket) {
			mListeners.add(socket);
		}
	};

	public void listen(AsyncServer server, int port) {
		server.listen(null, port, mListenCallback);
	}

	private CompletedCallback mCompletedCallback;

	public void setErrorCallback(CompletedCallback callback) {
		mCompletedCallback = callback;
	}

	public CompletedCallback getErrorCallback() {
		return mCompletedCallback;
	}

	private void report(Exception ex) {
		if (mCompletedCallback != null)
			mCompletedCallback.onCompleted(ex);
	}

	public void listen(int port) {
		listen(AsyncServer.getDefault(), port);
	}
}
