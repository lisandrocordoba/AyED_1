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
        // Guardo los primeros k routers del maxHeap
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
        // IMPLEMENTAR
        return null;
    }
}

