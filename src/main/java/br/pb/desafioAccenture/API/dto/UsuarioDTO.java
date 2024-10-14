package br.pb.desafioAccenture.API.dto;

import java.util.HashMap;
import java.util.List;

public class UsuarioDTO {

    private String userId;
    private String userName;
    private String password;
    private List<HashMap<String, String>> collectionOfIsbns;

    public UsuarioDTO() {

    }
    public UsuarioDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<HashMap<String, String>> getCollectionOfIsbns() {
        return collectionOfIsbns;
    }

    public void setCollectionOfIsbns(List<HashMap<String, String>> collectionOfIsbns) {
        this.collectionOfIsbns = collectionOfIsbns;
    }
}