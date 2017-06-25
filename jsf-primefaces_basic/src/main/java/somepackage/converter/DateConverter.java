package somepackage.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;


@FacesConverter(forClass=DateConverter.class, value="somepackage.converter.DateConverter")
public class DateConverter implements Converter {
	
	private String pattern = "yyyy-MM-dd";
	
	public DateConverter() {
		
	}

	public DateConverter(String pattern) {
		
		this.pattern = pattern;
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = sdf.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String str = sdf.format(value);
		return str;
	}

}
