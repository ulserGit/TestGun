package API.Data;

import java.util.HashMap;
import java.util.Map;

public class Collections {

    public static Map<Integer,String> getPet(){
        Map<Integer,String> pet = new HashMap<>();
        pet.put(1, "{\"id\":998844770,\"category\":{\"id\":0,\"name\":\"string\"},\"name\":" +
                "\"TestGun\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":" +
                "\"string\"}],\"status\":\"available\"}");
        pet.put(2, "{\"id\":998844770,\"category\":{\"id\":0,\"name\":\"string\"},\"name\":" +
                "\"TestGunRename\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":" +
                "\"string\"}],\"status\":\"available\"}");
        return pet;
    }


}
