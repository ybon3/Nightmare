package jb.lazywork.httpserver;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/**
 * simple HTTP server in Java using only Java SE API
 * <p>
 * <a href='https://stackoverflow.com/questions/3732109/simple-http-server-in-java-using-only-java-se-api'>reference</a>
 */
public class Test {
	public static void main(String[] args) throws Exception {
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
		server.createContext("/test", new MyHandler());
		server.setExecutor(Executors.newCachedThreadPool());
//		server.setExecutor(null); // creates a default executor
		server.start();
	}

	static class MyHandler implements HttpHandler {
		@Override
		public void handle(HttpExchange t) throws IOException {
			System.out.println("METHOD: " + t.getRequestMethod());
			try {Thread.sleep(5000);} catch (Exception e) {}
			System.out.println("done.");
			String response = "This is the response";
			t.sendResponseHeaders(200, response.length());
			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
	}
}