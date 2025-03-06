package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DistroController {
	public DistroController() {
		super();
	}
	private String os() {
		return System.getProperty("os.name");
	}
	public void exibeDistro() {
		String os = os();
		String proc;
		if(os.contains("Linux")) {
			proc = "cat /etc/os-release";
		} else {
			System.out.println("OS inválido");
			return;
		}
		String[] procarr = proc.split(" ");
		try {
			Process p = Runtime.getRuntime().exec(procarr);
			InputStream flow = p.getInputStream();
			InputStreamReader reader = new InputStreamReader(flow);
			BufferedReader buffer = new BufferedReader(reader);
			String linha;
			String temp = null;
			String b;
			while((linha = buffer.readLine()) != null) {
				if(linha.contains("NAME=") && !linha.contains("PRETTY_NAME")) {
					temp = linha.split("=")[1];
				} else if(linha.contains("VERSION=")) {
					b = linha.split("=")[1];
					if(temp != null) {
						System.out.println("Nome: " + temp + "\nVersão da distribuição: " + b );
					}
				}
			}
			buffer.close();
			reader.close();
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
	}
}
