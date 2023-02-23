package com.konloch.stringvars;

/**
 * @author Konloch
 * @since 2/10/2023
 */
public class StringVars
{
	/**
	 * Search for the delimiter, and if it exists it will use the String between the delimiter as a key to do a key-value search.
	 *
	 * @param delimiter any character to represent the start and end of the variable key
	 * @param originalRSF an implementation of the original getStringValue function this API is going to be replacing
	 * @param rsf an implementation of a String key / value search
	 * @return the fully extracted String
	 */
	public static String getVariableValue(char delimiter, OriginalReturnString originalRSF,
	                                      ReturnStringFromKey rsf)
	{
		String value = originalRSF.getString();
		
		char[] chars = value.toCharArray();
		StringBuilder parsedValue = new StringBuilder();
		StringBuilder parsedKey = new StringBuilder();
		boolean escaped = false;
		boolean parsingKey = false;
		for(int i = 0; i < value.length(); i++)
		{
			char c = chars[i];
			
			if(escaped && c != delimiter)
			{
				escaped = false;
				parsedValue.append('\\');
			}
			
			if (c == '\\')
			{
				escaped = true;
			}
			else if (c == delimiter)
			{
				if (escaped)
				{
					parsedValue.append(c);
					escaped = false;
					continue;
				}
				
				parsingKey = !parsingKey;
				
				if (!parsingKey)
				{
					//handle recursion here
					try
					{
						String parsedKeyCopy = parsedKey.toString();
						String parsedKeyCopyValue = rsf.getString(parsedKeyCopy);
						parsedValue.append(parsedKeyCopyValue);
					}
					catch (StackOverflowError e)
					{
						e.printStackTrace();
					}
					
					parsedKey = new StringBuilder();
				}
			}
			else
			{
				escaped = false;
				if (parsingKey)
					parsedKey.append(c);
				else
					parsedValue.append(c);
			}
		}
		
		return parsedValue.toString();
	}
	
	/**
	 * Alert that this is a library
	 *
	 * @param args program launch arguments
	 */
	public static void main(String[] args)
	{
		throw new RuntimeException("Incorrect usage - for information on how to use this correctly visit https://konloch.com/StringVars/");
	}
}
