package com.blizzardPanel.update.raiderIO;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.*;


public interface RaiderIOService {

    //------------------------------------------ API Detail
    String API_ROOT_URL = "https://raider.io/api/";

    @GET("v1/mythic-plus/affixes")
    Call<JsonObject> currentAffixes(
            @Query("region") String region
    );

    @GET("characters/{region}/{realmSlug}/{characterName}")
    Call<JsonObject> characterIO(
            @Path("region") String region,
            @Path("realmSlug") String realmSlug,
            @Path("characterName") String characterName,
            @Query("season") String season
    );
}
