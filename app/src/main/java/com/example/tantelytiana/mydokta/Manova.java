package com.example.tantelytiana.mydokta;

/**
 * Created by Tantely Tiana on 01/07/2016.
 */
public class Manova {

    String adresse;
    public String groupeKey;
    public Boolean ajout2;



    Manova()
    {


    }


    public String manampiditra (String json, Character car,String car2 )
    {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < json.length(); i++) {

            if (json.charAt(i)== car)

            {
                str.append(car2);

            }
            else {
                str.append(json.charAt(i));

            }

        }
        return str.toString();

    }



    public String manampiditra2 (String json, Character car,Character car2 )
    {
        StringBuilder str = new StringBuilder();
        int i=0;
        while ( i < json.length()) {


            if ((json.charAt(i)== car) && (json.charAt(i+2)== car2) )

            {
                str.append(json.charAt(i));
                i++;

            }
            else {
                str.append(json.charAt(i));



            }
            i++;

        }
        return str.toString();

    }

    public String manampiditra3 (String json, Character car,Character car2 )
    {
        StringBuilder str = new StringBuilder();
        int i=0;
        while ( i < json.length()) {


            if ((json.charAt(i)== car) && (json.charAt(i-1)== car2) )

            {



            }
            else {

                str.append(json.charAt(i));

            }
            i++;

        }
        return str.toString();

    }


    public String manampiditra4 (String json, Character car )
    {
        StringBuilder str = new StringBuilder();
        int i=0;
        while ( i < json.length()) {


            if ((json.charAt(i)== car) )

            {



            }
            else {

                str.append(json.charAt(i));

            }
            i++;

        }
        return str.toString();

    }
}

