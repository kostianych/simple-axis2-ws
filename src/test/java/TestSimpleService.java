import static org.junit.Assert.assertEquals;

import java.rmi.RemoteException;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.junit.Test;
import org.junit.Before;
import java.lang.Exception;
/*import com.gmail.kostianych.HelloServiceDocument;
import com.gmail.kostianych.HelloServiceDocument.HelloService;
import com.gmail.kostianych.HelloServiceResponseDocument;
import com.gmail.kostianych.HelloServiceResponseDocument.HelloServiceResponse;
import com.gmail.kostianych.ws.SimpleServiceStub;*/
//import org.mortbay.jetty.Server;

public class TestSimpleService {
	
	private static EndpointReference targetEPR = 
	        new EndpointReference("http://localhost:8080/services/SimpleService");

    @Before
    public void setUp() throws Exception {
	//Server server = new Server(8080);
       // server.start();
        //server.join();
    }
	
	
	@Test
	public void testHelloWorldWebService() throws RemoteException {	    
	    Options options = new Options();
        options.setTo(targetEPR);
        options.setTransportInProtocol(Constants.TRANSPORT_HTTP);

        ServiceClient sender = new ServiceClient();
        sender.setOptions(options);        
        
        OMElement result = sender.sendReceive(getSayHelloMethod("world"));
        
        String response = result.getFirstElement().getText();        
        assertEquals("Hello world", response);
	}
	
	private OMElement getSayHelloMethod(String msg) {
	    OMFactory fac = OMAbstractFactory.getOMFactory();
	    OMNamespace omNs = fac.createOMNamespace("http://kostianych.gmail.com", "ns");

	    OMElement method = fac.createOMElement("sayHello", omNs);
	    OMElement value = fac.createOMElement("msg", omNs);
	    value.addChild(fac.createOMText(value, msg));
	    method.addChild(value);
	    return method;		
	}
	

}
