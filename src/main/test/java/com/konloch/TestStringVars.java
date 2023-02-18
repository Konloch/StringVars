package com.konloch;

import com.konloch.stringvars.StringVars;

import java.util.HashMap;

/**
 * @author Konloch
 * @since 2/18/2023
 */
public class TestStringVars
{
	public static void main(String[] args)
	{
		StringHolderExample example = new StringHolderExample()
		{
			@Override
			public String getString(String key)
			{
				return StringVars.getVariableValue('$', ()-> getMap().get(key),(vkey)-> getMap().get(vkey));
			}
		};
		
		example.getMap().put("example", "Example: $var$");
		example.getMap().put("var", "This is a great example of how it functions!");
		
		System.out.println("Results: " + example.getString("example"));
	}
	
	public static class StringHolderExample
	{
		private final HashMap<String, String> map = new HashMap<>();
		
		public String getString(String key)
		{
			return map.get(key);
		}
		
		public HashMap<String, String> getMap()
		{
			return map;
		}
	}
}
