package com.xiaozhengkeji.blog.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
@Data
@NoArgsConstructor
@Accessors( chain = true )
@TableName("user")
/**
 * (User)User
 *
 * @author XiaoZhengRS
 * @since 2020-02-10 18:39:26
 */
public class User implements Serializable {
    //@TableId(type = IdType.AUTO) 主键 自增
    //@TableField(exist = false) 表中不存在的字段

    private static final long serialVersionUID = -69480094938060638L;

    private Integer id;

    private String username;

    private String password;

    private String email;

    private String role;

    private Integer state;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

}
