package aed;

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
        // Se que los digitos van de 0 a 9 y que un octeto a lo sumo será 255 (A lo sumo 3 dígitos).
        ListaEnlazada<IPv4Address>[] bucket = new ListaEnlazada[10];
        int cantIP = ipv4.length;
        // Convierto las IPs de String a IPv4Address
        IPv4Address[] IPs = new IPv4Address[cantIP];
        for (int i = 0; i < cantIP; i++){
            IPs[i] = new IPv4Address(ipv4[i]);
        }
        // Ordeno con radix sort
        for (int i = 1; i <= 3; i++){
            
        }
        return null;
    }
}

