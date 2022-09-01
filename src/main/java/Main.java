import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.ProxyServerType;

import javax.ws.rs.core.Response;

public class Main {

    public static void main(String[] args){
        if(args.length <= 0){
            stop("The script needs at least 1 parameter.");
        }
        String action = args[0];
        if("proxySocksWithAuthent".equals(action)){
            proxySocksWithAuthent();
        }
        else{
            stop("Unknown action.");
        }
    }

    private static void stop(String msg){
        System.err.printf("%s\nThat's all.", msg);
        System.exit(1);
    }

    /**
     * This example fails when SOCKS ask for authentication.
     * I use this docker image to have a dante proxy SOCKS5 server:
     * https://github.com/ypiel-talend/docker-dante
     * As explained in the READM.md you can build an image with
     * or wirthout required authentication.
     *
     * This example fails when authentication is asked but is
     * successful when no authentication is needed.
     *
     */
    private static void proxySocksWithAuthent(){
        String url = "https://gist.githubusercontent.com/ypiel-talend/c3277c902b92a8e266d1eb3c0b0e576a/raw/956d70aa753af6cfa2a3a710a9150deff70b47f6/geologists_ok.json";
        String host = "127.0.0.1";
        int port = 1080;
        String login = "peter";
        String pwd = "aze123_=KLM";

        WebClient webClient = WebClient.create(url);
        HTTPConduit httpConduit = WebClient.getConfig(webClient).getHttpConduit();
        httpConduit.getClient().setProxyServer(host);
        httpConduit.getClient().setProxyServerPort(port);
        httpConduit.getClient()
                .setProxyServerType(ProxyServerType.SOCKS);
        httpConduit.getProxyAuthorization().setUserName(login);
        httpConduit.getProxyAuthorization().setPassword(pwd);

        Response res = webClient.invoke("GET", "");
        int status = res.getStatus();
        String body = res.readEntity(String.class);

        System.out.printf("Status: %s\nBody:\n%s", status, body);
    }

}
