package com.sergiomartinrubio.ldapjndiexample;

import javax.naming.Binding;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Properties;

import static javax.naming.Context.INITIAL_CONTEXT_FACTORY;
import static javax.naming.Context.PROVIDER_URL;

public class RootBindingExample {

    public static void main(String[] args) throws NamingException {
        Properties properties = new Properties();
        properties.setProperty(INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        // the port can be found in Apache Directory Studio
        properties.setProperty(PROVIDER_URL, "ldap://localhost:10389/dc=example,dc=com");
        DirContext context = new InitialDirContext(properties);

        NamingEnumeration<Binding> bindings = context.listBindings("");
        while (bindings.hasMoreElements()) {
            System.out.println(bindings.next().getName());
        }
    }

}
