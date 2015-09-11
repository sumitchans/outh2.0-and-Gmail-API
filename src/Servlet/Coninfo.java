package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTMLDocument.Iterator;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.gmail.Gmail;
//import com.google.api.services.gmail.Gmail.Users.Message.List;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePartHeader;
import com.google.api.services.gmail.model.Profile;

/**
 * Servlet implementation class Coninfo
 */
public class Coninfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Coninfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String date=request.getParameter("DATE");
		String token=request.getParameter("CODE");
		SimpleDateFormat fort = new SimpleDateFormat("yyyy/MM/dd");
		String dt=null,pre = null;
		HashMap<String,Integer> has=new HashMap<String,Integer>();
		try{
			Date dts=(Date) fort.parse(date);
			 
			Calendar c = Calendar.getInstance();
			c.setTime(dts);
			dt=fort.format(dts);
			c.add(c.DATE,-90);
		Date	prev =(Date) c.getTime();
              pre=fort.format(prev);			
		}catch(Exception e){System.out.println(e);}
		
	
		GoogleCredential gl=	new GoogleCredential().setAccessToken(token);//.getRefreshToken();
		//String st=gl.getAccessToken();
		  Gmail service = new Gmail.Builder(new NetHttpTransport(),
				  JacksonFactory.getDefaultInstance(),gl)
			      .build();
	//Gmail.AccountApi.
		  Gmail.Users.GetProfile ls= service.users().getProfile("me");
		  String query="before:"+dt+"after:"+pre;
		  ListMessagesResponse ms=service.users().messages().list("me").setQ(query).execute();
		  ArrayList<Message> mls=new ArrayList<Message>();
	       mls.addAll(ms.getMessages());
	       
	       
	       Profile fl= ls.execute();
	       String email=fl.getEmailAddress();
	       //String name=fl.getName();
	       PrintWriter out=response.getWriter();
		 out.println("<HTML>");
			out.println("<HEAD>");
			out.println("<TITLE>Servlet Testing</TITLE>");
			out.println("</HEAD>");
			out.println("<BODY>");
			out.println("<BODY>");
			out.println("<h1>Welcome User:      </h1><h1><a href=https://accounts.google.com/logout>LOGOUT</a></h1>");
			out.println("<h1>"+email+"</h1>");
			out.println("<h1>Start Date:"+dt+"</h1>");
			out.println("<h1>End Date:"+pre+"</h1>");
			for(int i=0;i<mls.size();i++){
				
				//out.println("<h1>"+mls.get(i)+"</h1>");	
				Message t=service.users().messages().get("me",mls.get(i).getId()).execute();
				//out.println("<h1>"+t+"</h1>");		
				ArrayList<MessagePartHeader>  pr=(ArrayList<MessagePartHeader>) t.getPayload().getHeaders();
						String lst="";
						 for(int j=0;j<pr.size();j++){
							//out.println("<h1>"+pr.get(j)+"</h1>");
							if(((MessagePartHeader) pr.get(j)).getName().equalsIgnoreCase("To")){
								lst=((MessagePartHeader) pr.get(j)).getValue();
								if(!lst.equalsIgnoreCase(email)){
								if(!has.containsKey(lst)){
									has.put(lst,1);
									
								}else{
									has.put(lst,has.get(lst)+1);
									
								}}
								//out.println("<h1> to ="+lst+"</h1>");
							}
						 if(pr.get(j).getName().equalsIgnoreCase("Bcc")){
							 lst=pr.get(j).getValue();
							 if(!lst.equalsIgnoreCase(email)){
							 if(!has.containsKey(lst)){
									has.put(lst,1);
									
								}else{
									has.put(lst,has.get(lst)+1);
									
								}}
								//out.println("<h1> Bcc="+lst+"</h1>");
						 }
						 if(pr.get(j).getName().equalsIgnoreCase("cc")){
							 lst=pr.get(j).getValue();
							 if(!lst.equalsIgnoreCase(email)){
							 if(!has.containsKey(lst)){
									has.put(lst,1);
									
								}else{
									has.put(lst,has.get(lst)+1);
									
								}}}
							 if(pr.get(j).getName().equalsIgnoreCase("From")){
								 lst=pr.get(j).getValue();
								 if(!lst.equalsIgnoreCase(email)){
								 if(!has.containsKey(lst)){
										has.put(lst,1);
										
									}else{
										has.put(lst,has.get(lst)+1);
										
									}}
								//out.println("<h1> cc="+lst+"</h1>");
							 
						 }
			}}
						 out.println("<h1>Email------------------------------------#Conv</h1>");
						 Set<Entry<String, Integer>> set = has.entrySet();
					        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
					        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
					        {
					            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
					            {
					                return (o2.getValue()).compareTo( o1.getValue() );
					            }
					        } );
					        for(Map.Entry<String, Integer> entry:list){
					            out.println("<h1>"+entry.getKey()+" ==== "+entry.getValue()+"</h1>");
					        }
						
			out.println("<BODY>");
			out.println("<HTML>");
	}

}

