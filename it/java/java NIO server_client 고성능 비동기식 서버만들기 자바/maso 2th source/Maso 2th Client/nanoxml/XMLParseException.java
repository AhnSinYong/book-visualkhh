/* This file is part of NanoXML.
 *
 * $Revision: 1.1.1.1 $
 * $Date: 2000/07/26 19:30:49 $
 * $Name:  $
 *
 * Copyright (C) 2000 Marc De Scheemaecker, All Rights Reserved.
 *
 * This software is provided 'as-is', without any express or implied warranty.
 * In no event will the authors be held liable for any damages arising from the
 * use of this software.
 *
 * Permission is granted to anyone to use this software for any purpose,
 * including commercial applications, and to alter it and redistribute it
 * freely, subject to the following restrictions:
 *
 *  1. The origin of this software must not be misrepresented; you must not
 *     claim that you wrote the original software. If you use this software in
 *     a product, an acknowledgment in the product documentation would be
 *     appreciated but is not required.
 *
 *  2. Altered source versions must be plainly marked as such, and must not be
 *     misrepresented as being the original software.
 *
 *  3. This notice may not be removed or altered from any source distribution.
 */


package nanoxml;


/**
 * An XMLParseException is thrown when an error occures while parsing an XML
 * string.
 * <P>
 * $Revision: 1.1.1.1 $<BR>
 * $Date: 2000/07/26 19:30:49 $<P>
 *
 * @see nanoxml.XMLElement
 *
 * @author Marc De Scheemaecker
 *         &lt;<A HREF="mailto:Marc.DeScheemaecker@advalvas.be"
 *         >Marc.DeScheemaecker@advalvas.be</A>&gt;
 * @version 1.4
 */
public class XMLParseException
    extends RuntimeException
{

   /**
    * Creates an exception.
    *
    * @param tag     The name of the tag where the error is located.
    * @param message A message describing what went wrong.
    */
   public XMLParseException(String tag,
                            String message)
   {
      super("XML Parse Exception during parsing of "
            + ((tag == null) ? "the XML definition" : ("a " + tag + "-tag"))
            + ": " + message);
   }


   /**
    * Creates an exception.
    *
    * @param tag     The name of the tag where the error is located.
    * @param lineNr  The number of the line in the input.
    * @param message A message describing what went wrong.
    */
   public XMLParseException(String tag,
                            int    lineNr,
                            String message)
   {
      super("XML Parse Exception during parsing of "
            + ((tag == null) ? "the XML definition" : ("a " + tag + "-tag"))
            + " at line " + lineNr + ": " + message);
   }    
    
}
