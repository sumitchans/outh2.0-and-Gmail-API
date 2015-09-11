package Servlet;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.portable.InputStream;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeServlet;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.GmailScopes;

/**
 * Servlet implementation class LoginAuth1
 */
public class LoginAuth1 extends AbstractAuthorizationCodeServlet {
	private static final long serialVersionUID = 1L;
	//private static final java.io.File DATA_STORE_DIR = new java.io.File(
		       // System.getProperty("user.dir"), "/ZapStittch");
	private static FileDataStoreFactory DATA_STORE_FACTORY;
	 private static final JsonFactory JSON_FACTORY =JacksonFactory.getDefaultInstance();
  // java.io.InputStream in =LoginAuth1.class.getResourceAsStream("client_secret.json");
    /**
     * @see HttpServlet#HttpServlet()
     */
	//String path="";
    public LoginAuth1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */


	@Override
	protected String getRedirectUri(HttpServletRequest req)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		 GenericUrl url = new GenericUrl(req.getRequestURL().toString());
		    url.setRawPath("/ZapStittch/Confirm");
		    return url.build();
	}

	@Override
	protected String getUserId(HttpServletRequest arg0)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String str=arg0.getParameter("acces_token");;
		return null;//return arg0.getParameter("acces_token");
	}

	@Override
	protected GoogleAuthorizationCodeFlow initializeFlow() throws ServletException,
			IOException {
	String path=getServletContext().getRealPath("/client_secret_47196805398-cp80fa9kdoh8iqar5h61vfi2f2idofsa.apps.googleusercontent.com (5).json");
	 GoogleClientSecrets clientSecrets =
		      GoogleClientSecrets.load(JSON_FACTORY, new FileReader(path));

		    return new GoogleAuthorizationCodeFlow.Builder(
		      new NetHttpTransport(), JacksonFactory.getDefaultInstance(), clientSecrets,
		      Arrays.asList("https://www.googleapis.com/auth/gmail.readonly"))
		      .setAccessType("offline").build();

	}

}
