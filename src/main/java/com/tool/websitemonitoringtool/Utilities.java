package com.tool.websitemonitoringtool;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.tool.websitemonitoringtool.pojo.Status;

public class Utilities {

	private Utilities() {
	}

	public static Status pingURL(String url, int timeout) {
		url = url.replaceFirst("^https", "http"); // Otherwise an exception may be thrown on invalid SSL certificates.

		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setConnectTimeout(timeout);
			connection.setReadTimeout(timeout);
			connection.setRequestMethod("HEAD");
			int responseCode = connection.getResponseCode();
			if (200 <= responseCode && responseCode <= 399)
				return Status.UP;
		} catch (IOException exception) {
			return Status.DOWN;
		}

		return Status.DOWN;
	}
}
