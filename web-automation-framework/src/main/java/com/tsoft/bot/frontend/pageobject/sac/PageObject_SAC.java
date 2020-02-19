package com.tsoft.bot.frontend.pageobject.sac;

import java.net.PortUnreachableException;

public class PageObject_SAC {

    public static String TXT_USUARIO            = "formLoginUsuario:txtUsuario"; //id
    public static String TXT_CONTRASENIA        = "formLoginUsuario:pwdContrasenia"; //id
    public static String BTN_ENTRAR             = "/html/body/div[1]/div[2]/div/div/form/table/tbody/tr/td/table/tbody/tr[4]/td[1]/button/span[2]"; //xpath
    public static String BTN_BANDEJA            = "/html/body/div[1]/div[2]/form/div/ul/li[2]/a";
    public static String BTN_CONSULTAORDENES    = "/html/body/div[1]/div[2]/form/div/ul/li[2]/ul/li[2]/a/span[2]"; //xpath
    public static String TXT_CELULAR            = "formGestorOrdenes:txtTelefono";
    public static String LIST_ANTIGUADAD        = "/html/body/div[1]/div[3]/form/div/div/div[2]/div[1]/table/tbody/tr[1]/td[10]/div/div[3]";
    public static String SELECT_UNASEMANA       = "/html/body/div[5]/div/ul/li[3]";
    public static String BTN_BUSCARORDENES      = "formGestorOrdenes:search";
    public static String BTN_SEARCHIBG          = "/html/body/div[1]/div[2]/form/div/ul/li[3]/a";
    public static String BTN_GESTORDIBG         = "/html/body/div[1]/div[2]/form/div/ul/li[3]/ul/li/a/span[2]";
    public static String LIST_FILTROAVAN        = "/html/body/div[1]/div[3]/form/div[1]/div/div[3]/div/div[1]/div[1]/div[1]/table/tbody/tr[3]/td[3]/div/div[3]/span";
    public static String SELECT_TELEFONO        = "/html/body/div[6]/div/ul/li[7]";
    public static String TXT_CELULAR2           = "formGestorOrdenes:tabview:filtroTelefono";
    public static String BTN_CONSULTARORDENES2  = "formGestorOrdenes:tabview:btnBuscarOrdenes";
}
