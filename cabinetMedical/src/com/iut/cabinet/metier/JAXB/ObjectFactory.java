//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.02.07 at 06:06:36 PM CET 
//


package com.iut.cabinet.metier.JAXB;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.iut.cabinet.metier.JAXB package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ListePatients_QNAME = new QName("", "listePatients");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.iut.cabinet.metier.JAXB
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PatientType }
     * 
     */
    public PatientType createPatientType() {
        return new PatientType();
    }

    /**
     * Create an instance of {@link ListePatientsType }
     * 
     */
    public ListePatientsType createListePatientsType() {
        return new ListePatientsType();
    }

    /**
     * Create an instance of {@link AdresseType }
     * 
     */
    public AdresseType createAdresseType() {
        return new AdresseType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListePatientsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "listePatients")
    public JAXBElement<ListePatientsType> createListePatients(ListePatientsType value) {
        return new JAXBElement<ListePatientsType>(_ListePatients_QNAME, ListePatientsType.class, null, value);
    }

}
