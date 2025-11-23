Zbudowałem serwis rezerwacji w Spring Boot 3 / java 21. Miałem drobne problemy z konkurencyjnością co wymagało drobnego doedukowania 

Compare-And-Swap - Dodałem do rekordu produktu kolumnę version. Zapis do bazy działa tylko wtedy, gdy version się zgadza

Transakcje i Retries - zaimplementowałem pętle for, która próbuje retries 3 razy zanim wrzuci błąd. Pozwoliłem sobie dodać małą obsługę błędów co poprawia ich czytelność
(obsługa zaimplementowana z mojego ostatniego gotowego projektu). Transakcje są wykonywane w osobnym serwisie i wstrzyknięte do docelowego w celu polepszenia czytelności 
i z uwagi na warunek by nie robic transakcji całości.

architektura - standardowa warstwa spring boot controller -> service -> repository
