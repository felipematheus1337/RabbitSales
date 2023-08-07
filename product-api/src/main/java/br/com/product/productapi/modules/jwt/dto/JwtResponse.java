package br.com.product.productapi.modules.jwt.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.jsonwebtoken.Claims;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

    private Integer id;
    private String name;
    private String email;


    public static JwtResponse getUser(Claims jwtClaims) {
        try {
            return  new ObjectMapper().convertValue(jwtClaims.get("authUser"), JwtResponse.class);
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
