package com.sergiomartinrubio.ldapjndiexample;

import javax.naming.Binding;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Properties;

import static javax.naming.Context.INITIAL_CONTEXT_FACTORY;
import static javax.naming.Context.PROVIDER_URL;

public class OrganizationalUnitExample {

    public static void main(String[] args) throws NamingException {
        Properties properties = new Properties();
        properties.setProperty(INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        // the port can be found in Apache Directory Studio
        properties.setProperty(PROVIDER_URL, "ldap://localhost:10389/dc=example,dc=com");
        DirContext context = new InitialDirContext(properties);

        DirContext peopleContext = (DirContext) context.lookup("ou=people");

        NamingEnumeration<Binding> people = peopleContext.listBindings("");
        while (people.hasMoreElements()) {
            String bindingName = people.next().getName();
            Attributes personAttributes = peopleContext.getAttributes(bindingName);
            Attribute description = personAttributes.get("description");
            Attribute mailsAttribute = personAttributes.get("mail");
            Attribute personName = personAttributes.get("cn");

            NamingEnumeration<?> personNames = personName.getAll();
            System.out.println("Person names:");
            while (personNames.hasMoreElements()) {
                System.out.println(personNames.next());
            }
            System.out.println();

            System.out.printf("Description: %s\n\n", description.get());

            NamingEnumeration<?> mails = mailsAttribute.getAll();
            System.out.println("Mails:");
            while (mails.hasMoreElements()) {
                System.out.println(mails.next());
            }
        }
    }
}
