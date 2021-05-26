.data
	naglowek:	.asciiz "Kalkulator! 260377\n"
	pytanieOperacja:	.asciiz "\nktora operacja? \n1 - dodawanie, 2 - odejmowanie 3 - mnozenie, 4 - dzielenie.\n5 - odwrotnosc, 6 - wartosc bezwzgl. 7 - potegowanie. 8 - silnia. 9 - MC.\n"
	pytanieNaWynik:	.asciiz "Czy chcesz dzialac na poprzednim wyniku TAK/NIE (1/0) \n"
	msg3:	.asciiz "Wynik Dodawania =  \n"
	msg4:	.asciiz "Wynik Odejmowania =  \n"
	msg5: 	.asciiz "Wynik Mnozenia =  \n"
	msg6: 	.asciiz "Wynik Dzielenia = \n"
	msg7: 	.asciiz "\nCzy chcesz kontynuowac? TAK/NIE (1/0) \n"
	msg8: 	.asciiz "Wynik Odwrotnosci = \n"
	msg9: 	.asciiz "Wynik Bezwzgledny = \n"
	msg10:	.asciiz "\n1 - (M+)	2 - (M-)	3 - (MC)\n"
	msgNo1: .asciiz "Wprowadz pierwsza liczbe \n"
	msgNo2: .asciiz "Wprowadz druga liczbe \n"
	msgMR: 	.asciiz "Czy wczytac wartosc z buforu MR? TAK/NIE (1/0)\n"
	bufor:	.asciiz "Bufor = "
	zeroAsFloat: .float 0.0
	oneAsFloat: .float 1.0

.text						# $t0 - operacja arytmetyczna
						# $t1 - pierwsza liczba / wynik operacji
						# $t2 - druga liczba operacji aryt.
						# $t3 - flaga czy chcemy wykonac dzialanie na poprzenim wyniku
						# $t4 - flaga czy chcemy wykonac inne dzialanie aryt/ ew blad wprowadzeniaoperacji
	
	main:					#glowna funkcja od ktorej sie zaczyna program
	

	jal displayNaglowek			#wysiwetl "Kalkulator!"
	

	while:					#glowna petla dzialania programu, petla czy kontunujemy dzialanie
	
	beq $t0, 0, skoknawynik			#w t0 mamy ostatnia operacje, jezeli 0<t0<5 to byla dobra operacja
						#za pierwszym razem musimy ominac pytanie o dzialanie na wyniku, poniewaz
						#ten wynik jeszce nie istneje
	
	jal displayOperacjaNaWyniku
	
	li $v0, 5				#wczytaj dane input do v0
	syscall
	
	move $t3, $v0				#przenies dane z input do t3
	
	skoknawynik:
	
	jal displayBufor
	
	jal displayOperacja
	

	li $v0, 5				#wczytaj dane input do v0
	syscall
	
	move $t0, $v0				#przechowaj dane z v0 to t0
	
	slti $t4, $t0,10 			#wybor operacji >=5
	beq $t4, 0, zlaopr			#jezeli zla opcja operacji to skocz na czesc koncowa
	
	slt $t4, $zero, $t0			#wybor operacji <=0
	beq $t4, 0, zlaopr			#jezeli zla opcja operacji to skocz na czesc koncowa

	beq $t0, 9, M3
	beq $t3, 1, skok			#jezeli chcemy dzialac na wyniku poprzedniej operacji to omijamy wczytywanie no1
	
	jal zapytanieMR
	
	li $v0, 5				#wczytaj dane input do v0
	syscall
	
	move $t4, $v0
	
	beq  $t4, 0, skipmr
	
	lwc1 $f2, zeroAsFloat
	add.s $f2, $f2, $f14
	j skipno1
	
	
	skipmr:
	jal displayNo1				#wysietl zapytanie o No1
	
	li $v0, 6				#wczytaj dane input do v0
	syscall
	
	lwc1 $f2, zeroAsFloat
	add.s $f2, $f2, $f0
	#move $f2, $f0				#przechowaj dane z v0 to t1
	
	skipno1:
	
	beq $t0, 5, skipsecond
	
	skok:	
	beq $t0, 5, skipsecond
	beq $t0, 6, skipsecond
	beq $t0, 8, skipsecond
	
	
	jal zapytanieMR
	li $v0, 5				#wczytaj dane input do v0
	syscall
	
	move $t4, $v0
	beq  $t4, 0, skipmr2
	lwc1 $f4, zeroAsFloat
	add.s $f4, $f4, $f14
	j skipsecond
	
	skipmr2:
	
	jal displayNo2				#wysietl zapytanie o No2
	
	li $v0, 6				#wczytaj dane input do v0
	syscall
	
	lwc1 $f4, zeroAsFloat
	add.s $f4, $f4, $f0
	#move $f4, $f0				#przechowaj dane z v0 to t2
	
	skipsecond:
	
	beq $t0, 1, dodawanie

	beq $t0, 2, odejmowanie

	beq $t0, 3, mnozenie
	
	beq $t0, 4, dzielenie
	
	beq $t0, 5, odwrotnosc
		
	beq $t0, 6, wbezwgledna
	
	beq $t0, 7, potegowanie
	
	beq $t0, 8, silnia
	
	displayBufor:
		li $v0, 4
		la $a0, bufor
		syscall
		lwc1 $f6, zeroAsFloat
		add.s $f12, $f14, $f6
		li $v0, 2
		syscall
			
		jr $ra		#powrot do lini po wywolaniu skoku

	displayNaglowek: 	#wyswietlenie naglowka programu
		li $v0, 4
		la $a0, naglowek
		syscall
		jr $ra		#powrot do lini po wywolaniu skoku
		
		
	displayOperacja:
		li $v0, 4
		la $a0, pytanieOperacja
		syscall
			
		jr $ra		#powrot do lini po wywolaniu skoku
		
			
	displayOperacjaNaWyniku:
		li $v0, 4
		la $a0, pytanieNaWynik
		syscall
		jr $ra		#powrot do lini po wywolaniu skoku
		
	displayNo1:
		li $v0, 4
		la $a0, msgNo1
		syscall
		jr $ra		#powrot do lini po wywolaniu skoku
		
	displayNo2:
		li $v0, 4
		la $a0, msgNo2
		syscall
		jr $ra		#powrot do lini po wywolaniu skoku

	zapytanieMR:
		li $v0, 4
		la $a0, msgMR
		syscall
		jr $ra		#powrot do lini po wywolaniu skoku
	dodawanie:
		li $v0, 4
		la $a0, msg3
		syscall	
		
		add.s  $f2, $f2, $f4
		
		j koniec	#skok do czesci koncowej
		
	odejmowanie:
		li $v0, 4
		la $a0, msg4
		syscall	
		
		sub.s $f2, $f2, $f4
		
		j koniec	#skok do czesci koncowej
		
	mnozenie:
		li $v0, 4
		la $a0, msg5
		syscall	
		mul.s $f2, $f2, $f4
		
		j koniec	#skok do czesci koncowej
	
	dzielenie:
		li $v0, 4
		la $a0, msg6
		syscall
		div.s $f2, $f2, $f4
		
		j koniec	#skok do czesci koncowej
		
	odwrotnosc:
		li $v0, 4
		la $a0, msg8
		syscall
		lwc1  $f4, oneAsFloat
		div.s  $f2, $f4, $f2
		
		j koniec	#skok do czesci koncowej
		
	wbezwgledna:
		li $v0, 4
		la $a0, msg9
		syscall
		abs.s $f2, $f2
		j koniec	#skok do czesci koncowej
		
	potegowanie: 
		
		lwc1 $f6, oneAsFloat
		lwc1 $f12, oneAsFloat
		pow:
			mul.s $f12, $f12, $f2
			sub.s $f4, $f4, $f6
			c.le.s $f6, $f4
			bc1t pow
  		 
  		lwc1 $f6, zeroAsFloat
  		add.s $f2, $f12, $f6
		j koniec	#skok do czesci koncowej
		
	silnia:
		lwc1 $f6, oneAsFloat
		lwc1 $f12, oneAsFloat
		fac:
			mul.s $f12, $f12, $f2
			sub.s $f2, $f2, $f6
			c.le.s $f6, $f2
			bc1t fac
			
		lwc1 $f6, zeroAsFloat
  		add.s $f2, $f12, $f6
		j koniec	#skok do czesci koncowej
		
	koniec:
		
		#add.s $f12, $f2, zeroAsFloat	#program wypisuje wynik aktualnego dzialania //nie wiem poi co to
		lwc1 $f6, zeroAsFloat
		add.s $f12, $f2, $f6
		li $v0, 2
		syscall
		
		li $v0, 4	#czy chcesz kontynuowac?
		la $a0, msg10
		syscall
		
		li $v0, 5
		syscall
		move $t4, $v0	# w tym momencie nadpisujemy rejestr
		beq $t4, 1, M1
		beq $t4, 2, M2	
		beq $t4, 3, M3	
		
		zlaopr:		

		li $v0, 4	#czy chcesz kontynuowac?
		la $a0, msg7
		syscall
		
		li $v0, 5
		syscall
		move $t4, $v0	# w tym momencie nadpisujemy rejestr
		beq $t4, 1, while
		
				#ta linia to sygna³ zakonczenia programu.
		li $v0, 10
		syscall


	M1:
		add.s  $f14, $f14, $f12
		j zlaopr
	M2:
		sub.s $f14, $f14, $f12
		j zlaopr
	M3:
		lwc1 $f14, zeroAsFloat
		j zlaopr
