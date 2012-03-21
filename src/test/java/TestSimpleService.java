import static org.junit.Assert.assertEquals;

import java.rmi.RemoteException;

import org.junit.Test;

import com.gmail.kostianych.HelloServiceDocument;
import com.gmail.kostianych.HelloServiceDocument.HelloService;
import com.gmail.kostianych.HelloServiceResponseDocument;
import com.gmail.kostianych.HelloServiceResponseDocument.HelloServiceResponse;
import com.gmail.kostianych.ws.SimpleServiceStub;


public class TestSimpleService {
	
	@Test
	public void testHelloWorldWebService() throws RemoteException {
		SimpleServiceStub stub = new SimpleServiceStub("http://localhost:8080/services/SimpleService");
		HelloServiceDocument helloServiceDocument = HelloServiceDocument.Factory.newInstance();
		HelloService helloService = helloServiceDocument.addNewHelloService();
		helloService.setMsg("world");
		HelloServiceResponseDocument respDoc = stub.helloService(helloServiceDocument);
		HelloServiceResponse resp = respDoc.getHelloServiceResponse();
		assertEquals("Hello world", resp.getReturn());
	}

}
