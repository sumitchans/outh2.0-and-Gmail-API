package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonFactory;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.Gmail.Users.GetProfile;
import com.google.api.services.gmail.model.Draft;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePart;
import com.google.api.services.gmail.model.MessagePartHeader;
import com.google.api.services.gmail.model.Profile;

/**
 * Servlet implementation class Confirm
 */
public class Confirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Confirm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			PrintWriter out=response.getWriter();
		String token=request.getParameter("code");
		//String error=request.getParameter("error");
		if(token!=null){
		GoogleAuthorizationCodeTokenRequest gt=new GoogleAuthorizationCodeTokenRequest(new NetHttpTransport(),
				JacksonFactory.getDefaultInstance(),
				"47196805398-cp80fa9kdoh8iqar5h61vfi2f2idofsa.apps.googleusercontent.com",
				"R4-W9cbWQqWsTbVL3jxKTq4t",
				token,
				"http://localhost:8080/ZapStittch/Confirm").setScopes(Arrays.asList(
	 			          "https://www.googleapis.com/auth/userinfo.email",
	 			          "https://www.googleapis.com/auth/userinfo.profile"));
		GoogleTokenResponse gs=gt.execute();
		String tokenId=gs.getAccessToken();
		//String tokenId=gs.getRefreshToken();
		GoogleCredential gl=	new GoogleCredential().setAccessToken(tokenId);
	//String st=gl.getAccessToken();
	  Gmail service = new Gmail.Builder(new NetHttpTransport(),
			  JacksonFactory.getDefaultInstance(),gl)
		      .build();
	  Gmail.Users.GetProfile ls= service.users().getProfile("me");
	  ListMessagesResponse ms=service.users().messages().list("me").execute();
	 
     Profile fl= ls.execute();
     String email=fl.getEmailAddress();
     String msg="" +fl.getMessagesTotal();
       ArrayList<Message> mls=new ArrayList<Message>();
       mls.addAll(ms.getMessages());
       out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Servlet Testing</TITLE>");
			out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<h1>Welcome User:      </h1><h1><a href=https://accounts.google.com/logout>LOGOUT</a></h1>");
		out.println("<h1>Email Address:"+email+"</h1>");
		//out.println("<BODY>");
		out.println("<FORM ACTION=Coninfo METHOD=POST>");
		out.println("<INPUT TYPE=TEXT NAME=DATE>yyyy/MM/dd");
		out.println("<INPUT TYPE=HIDDEN NAME=CODE value="+tokenId+">");
		
		out.println("<INPUT TYPE=SUBMIT NAME=SUBMIT VALUE=SUBMIT>");
		out.println("</BODY>");
		out.println("<HTML>");
       
     
	}else{
		response.sendRedirect("http://localhost:8080/ZapStittch/LoginFailed");
		
	}
		}
	

}
