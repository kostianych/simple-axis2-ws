import static org.junit.Assert.assertEquals;

import java.rmi.RemoteException;

import org.junit.Test;
import org.junit.Before;
import java.lang.Exception;
/*import com.gmail.kostianych.HelloServiceDocument;
import com.gmail.kostianych.HelloServiceDocument.HelloService;
import com.gmail.kostianych.HelloServiceResponseDocument;
import com.gmail.kostianych.HelloServiceResponseDocument.HelloServiceResponse;
import com.gmail.kostianych.ws.SimpleServiceStub;*/
import org.mortbay.jetty.Server;

public class TestSimpleService {

    @Before
    public void setUp() throws Exception {
	Server server = new Server(8080);
        server.start();
        server.join();
    }
	
	
	@Test
	public void testHelloWorldWebService() throws RemoteException {
	    /*SimpleServiceStub stub = new SimpleServiceStub("http://localhost:8080/services/SimpleService");
		HelloServiceDocument helloServiceDocument = HelloServiceDocument.Factory.newInstance();
		HelloService helloService = helloServiceDocument.addNewHelloService();
		helloService.setMsg("world");
		HelloServiceResponseDocument respDoc = stub.helloService(helloServiceDocument);
		HelloServiceResponse resp = respDoc.getHelloServiceResponse();
		assertEquals("Hello world", resp.getReturn());*/
	}

}
