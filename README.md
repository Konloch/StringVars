# StringVars
StringVars is a zero dependency pure Java solution to create recursive String variables, such as $var$ or %var%.

## How To Add As Library
Add it as a maven dependency or just [download the latest release](https://github.com/Konloch/StringVars/releases).
```xml
<dependency>
  <groupId>com.konloch</groupId>
  <artifactId>StringVars</artifactId>
  <version>1.0.0</version>
</dependency>
```

## How To Use
[Click here for the StringHolderExample class](https://github.com/Konloch/StringVars/blob/main/src/main/test/java/com/konloch/TestStringVars.java)
```java
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
```