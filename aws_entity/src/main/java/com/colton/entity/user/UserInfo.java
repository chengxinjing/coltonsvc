package com.colton.entity.user;

import lombok.Data;
import org.apache.geode.pdx.PdxReader;
import org.apache.geode.pdx.PdxSerializable;
import org.apache.geode.pdx.PdxWriter;
import org.springframework.data.annotation.Id;
import org.springframework.util.StringUtils;

@Data
public class UserInfo implements PdxSerializable {
    @Id
    private String key;
    private String name;
    private String address;
    private String email;
    private String nickName;
    private int age;

    @Override
    public void toData(PdxWriter pdxWriter) {
        pdxWriter.writeString("key",key);
        pdxWriter.writeString("name",name);
        pdxWriter.writeString("address",address);
        pdxWriter.writeString("email",email);
        pdxWriter.writeString("nickName",nickName);
        pdxWriter.writeInt("age",age);

    }

    @Override
    public void fromData(PdxReader pdxReader) {
        this.key= pdxReader.readString("key");
        this.name =pdxReader.readString("name");
        this.address = pdxReader.readString("address");
        this.email = pdxReader.readString("email");
        this.age = pdxReader.readInt("age");
    }

    public String getKey(){
        if (StringUtils.isEmpty(key)){
            StringBuilder builder = new StringBuilder();
            key = builder.append(name).append("/").append(address).append("/").append(email).append("/").append(age).toString();
        }
        return key;
    }
}
