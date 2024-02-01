package ir.ac.kntu.kilid.dao;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class TokenRepository {
    public static final BiMap<String, String> TOKEN_REPOSITORY = HashBiMap.create();//username, token

}
