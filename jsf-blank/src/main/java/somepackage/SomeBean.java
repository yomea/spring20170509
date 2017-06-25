package somepackage;

import javax.faces.bean.ManagedBean;

/**
 * javax.faces.application.ViewExpiredException - /page-b.jsf - No saved view
 * state could be found for the view identifier: /page-b.jsf
 * 如果报以上错误，那是因为jsf的生命周期中的第一个为恢复视图，那就是会把你曾经操作过的视图缓存 ，如果找不到就报错了
 *	生成的页面中会包含以下代码，这个就是用来识别view的，如果服务器重启，页面没有刷新，那么就会报错
 * <input type="hidden" name="javax.faces.ViewState" id=
 * "j_id__v_0:javax.faces.ViewState:1" value=
 * "vSL5ncamPmvQKvDDTa4YclhmCwYXMkNAs2F0aU8CpgB0uwDljkW74ua+Uodi3dMUdyRQ4vlcqf6FomHnFocleCJZvNaMN/N4m6hqH+sG9ySyM4/yrnyh3G/FFxU="
 * autocomplete="off" />
 * 
 * @author may
 *
 */
@ManagedBean
public class SomeBean {
	private String someProperty = "Blah, blah";

	public String getSomeProperty() {
		return (someProperty);
	}

	public void setSomeProperty(String someProperty) {
		this.someProperty = someProperty;
	}

	public String someActionControllerMethod() {
		return ("page-b"); // Means to go to page-b.xhtml
							// (因为faces-config.xml中没有配置映射，所以以这个返回的字符串作为文件名)
	}

	public String someOtherActionControllerMethod() {
		return ("index"); // Means to go to index.xhtml (since condition is not
							// mapped in faces-config.xml)
	}
}
