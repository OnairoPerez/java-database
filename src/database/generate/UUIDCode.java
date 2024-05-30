/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.generate;

import java.util.UUID;

/**
 *
 * @author onairo
 */
public class UUIDCode {
    public String generate() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
