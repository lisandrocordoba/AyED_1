package aed;
import java.lang.Math;

public class InternetToolkit {
    public InternetToolkit() {
    }

    public Fragment[] tcpReorder(Fragment[] fragments) {
        int length = fragments.length;
        for (int i = 1; i < length; i ++){
            insertar(fragments, i, fragments[i]);
        }
        return fragments;
    }

    private void insertar(Fragment[] fragments, int posActual, Fragment valor){
        int j = posActual - 1;
        while (j >= 0 && valor.compareTo(fragments[j]) == -1){
            fragments[j + 1] = fragments[j];
            fragments[j] = valor;
            j--;
        }        
    }

    public Router[] kTopRouters(Router[] routers, int k, int umbral) {
        // Filtro los que pasan el umbral
        int length = routers.length;
        Router[] superanUmbral = new Router[length];
        int cuantosSuperan = 0;
        for (int i = 0; i < length; i ++){
            if (routers[i].getTrafico() >= umbral){
                superanUmbral[cuantosSuperan] = routers[i];
                cuantosSuperan ++;
            }
        }
         // Armo el maxHeap de router que superan el umbral
        maxHeap<Router> routersHeap = new maxHeap<Router>(superanUmbral, cuantosSuperan);

        // Guardo los primeros k routers del maxHeap en maxRouters
        Router[] maxRouters = new Router[k];
        int routersGuardados = 0;
        int i = 0;
        while(i < cuantosSuperan && routersGuardados < k){
            maxRouters[routersGuardados] = routersHeap.desapilar();
            i++;
            routersGuardados++;
        }
        return maxRouters;
    }

    public IPv4Address[] sortIPv4(String[] ipv4) {
        int cantIP = ipv4.length;

        // Inicializo el bucket de tamaño 10 (los digitos van de 0 a 9) y las listas en cada posicion
        ListaEnlazada<IPv4Address>[] bucket = new ListaEnlazada[10];
        for (int i = 0; i < 10; i++){
            bucket[i] = new ListaEnlazada<>();
        }
        
        // Convierto las IPs de String a IPv4Address
        IPv4Address[] IPs = new IPv4Address[cantIP];
        for (int i = 0; i < cantIP; i++){
            IPs[i] = new IPv4Address(ipv4[i]);
        }

        // RADIX SORT

        // Recorre del 4to al 1er octeto
        for (int octeto = 3; octeto >= 0; octeto--){

            // Recorre del 3er al 1er digito del octeto (a lo sumo tienen 3 digitos pues 255 es el maximo)
            for (int exp = 1; exp <= 3; exp++){

                // Recorre todas las IPs y las guarda en el bucket
                for (int i = 0; i < cantIP; i++){
                    int digito = (IPs[i].getOctet(octeto) % (int)(Math.pow(10, exp))) / (int)(Math.pow(10, exp - 1));
                    bucket[digito].agregarAtras(IPs[i]);
                }
                
                // Recorre el bucket
                for (int i = 0; i < 10; i++){
                    int ordenados = 0;

                    // Recorre las listas, guardando las IPs en el array original manteniendo el orden y borrandolas del bucket
                    while(bucket[i].longitud() > 0){
                        IPs[ordenados] = bucket[i].obtener(0);
                        bucket[i].eliminar(0);
                        ordenados++;
                    }
                }
            }
        }
        return IPs;
    }
}

