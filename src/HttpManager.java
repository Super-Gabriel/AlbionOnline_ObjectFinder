/**
 * clase para manejar solicitudes a api con url http
 * @author - G4BR13L t(>.<t)
 * @version - 1.0 | 24/03/23
 */
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public class HttpManager{
    /**
     * metodo get dado un url
     * @param url - url de solicitud
     * @return - la respuesta del servidor
     */
    public String getRequest(String url) throws InterruptedException, IOException{
	String form = "";

	try{
	    HttpRequest request = HttpRequest.newBuilder()
		.uri(URI.create(url))
		.method("GET", HttpRequest.BodyPublishers.noBody())
		.build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString()); 
            form = response.body();
	}catch(IOException | InterruptedException e){
	    System.out.println("error al mandar reporte");
	    throw e;
	}
	
	return form;
    }
   
}
