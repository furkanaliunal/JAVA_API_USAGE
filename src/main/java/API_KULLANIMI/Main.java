package API_KULLANIMI;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Scanner;

public class Main {

    private Scanner input = new Scanner(System.in);
    private static String key = "Buraya API Tokeninizi Yerlestirin";

    public static void main(String[] args) {
        System.out.println("Nobetci eczaneler uygulamasina hos geldin.");
        Main m = new Main();
        while(!m.start()){}

    }

    public Boolean start(){
        String il, ilce;
        System.out.print("Il= ");
        il = input.nextLine();
        System.out.print("Ilce= ");
        ilce = input.nextLine();

        HttpResponse<JsonNode> request = null;
        try{
            request = Unirest.get("https://api.collectapi.com/health/dutyPharmacy?ilce="+ilce+"&il="+il)
                    .header("content-type", "application/json")
                    .header("authorization", key)
                    .asJson();
        }catch(UnirestException e){
            System.out.println("Unirest hatası meydana geldi");
            return false;
        }

        JSONObject result = request.getBody().getObject();
            if (request.getStatus() != 200){
            return false;
        }
        if (result.has("status") && result.get("status").equals("404")){
            System.out.println("Hatalı şehir girildi");
            System.out.println("İşlem başa alınıyor...");
            return false;
        }

        JSONArray array = result.getJSONArray("result");
        System.out.println();
        System.out.println("Eczaneler:");
        System.out.println();
        for (Object o : array){
            //Pharmacy pharmacy = new Pharmacy((JSONObject)o);
            System.out.println(new Pharmacy((JSONObject)o));
            System.out.println();
        }
        return true;
    }
}
