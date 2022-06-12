# APRO2_22L_PRO_PT_AzulPro

Gra może zostać uruchomiona w dwóch trybach: konsolowym oraz graficznym.

Tryb konsolowy wspiera zarówno rozgrywkę lokalną, jak i sieciową.

Tryb graficzny wspiera tylko rozgrywkę lokalną (wersja sieciowa zawiera dużo bugów i jest trudno wyjaśnić jak poprawnie ją uruchomić,
dlatego też została na branchu newGameControll)

W celu zagrania w konsoli, należy uruchomić plik /src/main/java/server/Logic/Main.java.

W przypadku rozgrywki sieciowej, kolejnych graczy dołączamy, uruchamiając plik /src/main/java/client/network/Klient

W zależności od ilości instancji, tylu graczy dołączy.

W celu zagrania w trybie graficznym Offline, należy uruchomić plik /src/main/java/client/views/Main.java

Ilość graczy w trybie graficznym Offline ustalamy zmieniając linijkę 527 pliku /src/main/java/client/views/GameController.java