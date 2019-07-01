package br.edu.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

@RequestScoped
@FacesConverter(value = "formataDataHoraConverter")
public class DataHoraConverter extends DateTimeConverter implements Converter {

	public DataHoraConverter() {
		super();
		try {
			setTimeZone(TimeZone.getDefault());
			setPattern("dd/MM/yyyy kk:mm:ss");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object getAsObject(FacesContext contexto, UIComponent componente, String data) {

		Date dataConvertida = null;
		if (data != null
				&& !((data.replaceAll("/", "").replaceAll("_", "").replaceAll(":", "").trim()).length() == 0)) {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			formato.setLenient(false);
			try {
				dataConvertida = formato.parse(data);
			} catch (ParseException pe) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data inválida: " + data,
						"Data inválida: " + data);
				throw new ConverterException(facesMessage);
			}
		}

		return dataConvertida;
	}

}