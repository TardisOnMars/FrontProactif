/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.frontproactif.Action;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author svillenave
 */
public abstract class Action {
    public abstract boolean execute(HttpServletRequest request);
}
