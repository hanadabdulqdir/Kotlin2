package com.example.hanad.cucknorisapi.model;

/**
 * Created by hanad on 22/02/2018.
 */

public class Jokes {

   private String type;
   private Joke[] value;

   public Joke[] getValue() {
      return value;
   }

   public String getType() {
      return type;
   }
   /**
     * "id": 1,
     "joke": "Chuck Norris uses ribbed condoms inside out, so he gets the pleasure.",
     "categories": [
     "explicit"
     ]
     */
}
