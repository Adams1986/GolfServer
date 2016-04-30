package api;

import com.google.gson.JsonSyntaxException;
import controller.DataParser;
import controller.Logic;
import model.Member;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.HashMap;

@Path("/golfserver/api")
public class Api {

    private static final String MESSAGE_KEY = "message";

    @POST
    @Path("/login")
    @Produces("application/json")
    public Response authenticate(String data) {

        int statusCode;
        HashMap<String, String> dataMap = new HashMap<>();

        try {

            Member member = DataParser.getMember(data);
            boolean isAuthenticated = Logic.authenticateMember(member);

            if(isAuthenticated){
                statusCode = 200;
                dataMap.put(MESSAGE_KEY, "Login successful");
            }
            else {
                statusCode = 400;
                dataMap.put(MESSAGE_KEY, "Login fejlede. Der er fejl i brugernavn eller adgangskode");

            }

        }
        catch (JsonSyntaxException | NullPointerException e) {
            e.printStackTrace();
            statusCode = 400;
            dataMap.put(MESSAGE_KEY, "Der opstod en fejl. Prøv igen");
        }

        return Response
                .status(statusCode)
                .entity(DataParser.hashMapToJson(dataMap))
                .header("Access-Control-Allow-Headers", "*")
                .build();
    }

    @GET
    @Path("/members/{id}")
    @Produces("application/json")
    public Response getMember(@PathParam("id") String id){
        int statusCode;
        HashMap<String, String> dataMap = new HashMap<>();

        if (Logic.getMember(id) != null) {
            Member member = Logic.getMember(id);
            statusCode = 200;
            dataMap.put(MESSAGE_KEY, DataParser.memberToJson(member));
        }
        else {
            statusCode = 400;
            dataMap.put(MESSAGE_KEY, "Der opstod en fejl. Prøv igen");
        }

        return Response
                .status(statusCode)
                .entity(DataParser.hashMapToJson(dataMap))
                .header("Access-Control-Allow-Headers", "*")
                .build();
    }
}
