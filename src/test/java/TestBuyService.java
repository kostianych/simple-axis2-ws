import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.junit.Before;
import org.junit.Test;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.DefaultHandler;
import org.mortbay.jetty.handler.HandlerCollection;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;


public class TestBuyService {

	private static EndpointReference targetEPR = 
	        new EndpointReference("http://localhost:8085/services/ByeService");

    @Before
    public void setUp() throws Exception {
    	Server server = new Server(8085);
    	
//    	 Connector connector = new SelectChannelConnector();
//         connector.setPort(8085);
//         server.setConnectors(new Connector[] {connector});
        
        WebAppContext wactx = new WebAppContext();
        wactx.setParentLoaderPriority(true);
        wactx.setContextPath("/");
        wactx.setWar("src/main/webapp");

        HandlerCollection handlers = new HandlerCollection();
        handlers.setHandlers(new Handler[] {wactx, new DefaultHandler()});
        server.setHandler(handlers);
        server.start();
        //server.join();

    }
	
	
	@Test
	public void testByeWebService() throws RemoteException {	    
	    Options options = new Options();
        options.setTo(targetEPR);
        options.setTransportInProtocol(Constants.TRANSPORT_HTTP);

        ServiceClient sender = new ServiceClient();
        sender.setOptions(options);        
        
        OMElement result = sender.sendReceive(getSayHelloMethod("Kostia"));
        
        String response = result.getFirstElement().getText();        
        assertEquals("Bye Kostia", response);
	}
	
	private OMElement getSayHelloMethod(String msg) {
	    OMFactory fac = OMAbstractFactory.getOMFactory();
	    OMNamespace omNs = fac.createOMNamespace("http://kostianych.gmail.com", "ns");

	    OMElement method = fac.createOMElement("sayBye", omNs);
	    OMElement value = fac.createOMElement("msg", omNs);
	    value.addChild(fac.createOMText(value, msg));
	    method.addChild(value);
	    return method;		
	}

}
