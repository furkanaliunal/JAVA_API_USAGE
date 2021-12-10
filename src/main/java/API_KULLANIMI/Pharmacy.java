package API_KULLANIMI;

import org.json.JSONObject;

public class Pharmacy {
    private String name;
    private String address;
    private String phone;
    private String loc;
    public Pharmacy(JSONObject json){
        this.name = json.get("name").toString();
        this.address = json.get("address").toString();
        this.phone = json.get("phone").toString();
        this.loc = json.get("loc").toString();
    }

    @Override
    public String toString(){
        return this.name + " Eczanesi" + "\n\n" +
                "Adres: " + this.address + "\n" +
                "Iletisim: " + this.phone + "\n" +
                "Koordinatlari: " + this.loc + "\n";
    }


}
