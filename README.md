# ZoomInOutImageProject
READ ME

Proiectul se va deschide cu script.bat, dar va rog sa modificati prima linie, in loc de cd eclipse-workspace in functie de unde va fi dezarhivat pentru ca script-ul sa ruleze cum ar trebui.
Ca si structura, eu am un fisier docker-compose.yml care ajuta la deschiderea containerelor in ordinea potrivita, am creat jar-uri/war-uri build cu Maven pentru fiecare container pe care le-am rulat ulterior in Docker cu dockerfile-urile fiecaruia pentru testare. In primul container am creat atat serverul Javalin cat si un frontend basic, un plain HTML care are elemente JavaScript si CSS. Acesta a fost legat printr-un servlet de server. Structura mai departe este cea ceruta.
Este posibil (dar sper sa nu) sa dea anumite erori de dependinte, spre exemplu la versiunile de jdk, eu o am 21 ca si variabila de mediu si am folosit-o peste tot asa, iar pentru a evita acesta eroare trebuie modificata din sistem.
Am creat dockerfile-uri pentru fiecare container, este posibila si deschiderea fiecarui container pe rand.
Arhitectura se regaseste in architecture.pdf
Proiectul este unul complex si am depus foarte multa munca in crearea sa.
Va multumesc frumos, Vilceanu Diana-Maria
