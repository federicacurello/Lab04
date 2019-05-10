package it.polito.tdp.lab04.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		System.out.println(model.getTuttiICorsi());
		System.out.println(model.ottieniNome(146101).getCognome());
		System.out.println(model.getCorso("01KSUPG"));
		System.out.println(model.getStudentiIscrittiAlCorso(model.getCorso("01KSUPG")));
		System.out.println(model.cercaSuoiCorsi(179971));
		System.out.println(model.iscrittoAlCorso(179971, "01KSUPG"));
		System.out.println(model.iscriviStudenteACorso(model.ottieniNome(179971), model.getCorso("02AQJPG")));
		System.out.println(model.cercaSuoiCorsi(179971));
	}

}
