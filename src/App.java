import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        MaquinaDeCafe maquina = new MaquinaDeCafe();
        maquina.setCafetera(new Cafetera(50));
        maquina.setAzucarero(new Azucarero(20));
        maquina.setVasosPequenos(new Vaso(5, 3));
        maquina.setVasosMedianos(new Vaso(5, 5));
        maquina.setVasosGrandes(new Vaso(5, 7));

        try (Scanner entrada = new Scanner(System.in)) {
            while (true) {
                System.out.println("\nMAQUINA DE CAFE");
                System.out.println("1. Pequeno (3 oz)");
                System.out.println("2. Mediano (5 oz)");
                System.out.println("3. Grande (7 oz)");
                System.out.println("0. Salir");
                System.out.print("Selecciona una opcion: ");

                String opcion = entrada.nextLine().trim();
                if ("0".equals(opcion)) {
                    break;
                }

                String tipo;
                switch (opcion) {
                    case "1": tipo = "pequeno"; break;
                    case "2": tipo = "mediano"; break;
                    case "3": tipo = "grande"; break;
                    default:
                        System.out.println("Opcion invalida.");
                        continue;
                }

                System.out.print("Cucharadas de azucar: ");
                try {
                    int azucar = Integer.parseInt(entrada.nextLine().trim());
                    String resultado = maquina.getVasoDeCafe(
                        maquina.getTipoDeVaso(tipo), 1, azucar);
                    System.out.println(resultado);

                    if ("Felicitaciones".equals(resultado)) {
                        System.out.println("Puedes recoger tu cafe.");
                    }
                } catch (NumberFormatException error) {
                    System.out.println("Escribe una cantidad entera.");
                }
            }
        }
    }
}
