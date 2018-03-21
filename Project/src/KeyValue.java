
public class KeyValue 
{
	String key, value;
	 
	public KeyValue(String key, String value) 
	{
		this.key = key;
		this.value = value;
	}

	public String getValue() 
	{ 
		return value; 
	}
	
	public String getKey() 
	{ 
		return key;
	}
	
	public String toString()
	{ 
		return key; 
	}
	
	public boolean equals(Object kV) 
	{
		if (kV instanceof KeyValue) 
		{
			KeyValue kv = (KeyValue) kV;
			return (kv.value.equals(this.value));
		}
		return false;
	}
	
	
	
	
	
}
