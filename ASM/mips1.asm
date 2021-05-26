.data 
	mojeslowo: .word 23
	mojfloat: .float 13.33
	mojfloat2: .float 4.32





.text


  	sub $sp $sp 4
 	sw $ra ($sp)

 	move $s0, $a0 #tworzenie nowego stosu?

  	jal create_deque
  	move $s1, $v0
  	
  	
  	
  	lw $v0, mojfloat
  	
  	move $a0, $s1
  	move $a1, $v0
  	jal push_back_deque
  	
  	
  	lw $v0, mojfloat2
  	
  	
  	move $a0, $s1
  	move $a1, $v0
  	jal push_back_deque
  	
  	move $a0, $s1
	jal peek_back_deque
	mtc1 $v0, $f1
	
	
	mov.s $f12, $f1
	
	li $v0, 2
	syscall
	
	move $a0, $s1
	jal pop_back_deque
	#lwc1 $f2, 1($v0)
	move $a0, $s1
	jal peek_back_deque
	mtc1 $v0, $f1

 	mov.s $f12, $f1
 
  	li $v0, 2
	syscall
  	
  	li $v0, 10
	syscall





create_deque:
	li $a0, 12		#sizeof(Deque)
	li $v0, 9
	syscall
	move $t0, $v0

	li $t1, 0
	sw $t1, ($t0)
	sw $t1, 4($t0)
	sw $t1, 8($t0)

	move $v0, $t0

	jr $ra

push_back_deque:		#(a0 Deque, a1 Token)
	move $t0, $a0

	lw $t1, ($t0)
	addiu $t1, $t1, 1
	sw $t1, ($t0)
  
	li $a0, 12
	li $v0, 9
	syscall
	move $t1, $v0

	lw $t2, 8($t0)
	sw $t2, ($t1)
	sw $0, 4($t1)
	sw $a1, 8($t1)

	beq $0, $t2, push_back_deque_c0
	sw $t1, 4($t2)
push_back_deque_c0:
	lw $t2, 4($t0)
  
	bne $0, $t2, push_back_deque_c1
	sw $t1, 4($t0)
push_back_deque_c1:
	sw $t1, 8($t0)

	jr $ra
	
push_front_deque:                #(a0 Deque, a1 Token)
	move $t0, $a0

	lw $t1, ($t0)
	addiu $t1, $t1, 1
	sw $t1, ($t0)

	li $a0, 12
	li $v0, 9
	syscall
	move $t1, $v0
	
	lw $t2, 4($t0)
	sw $t2, 4($t1)
	sw $a1, 8($t1)
	sw $0, ($t1)
	
	beq $0, $t2, push_front_deque_c0
	sw $t1, ($t2)
push_front_deque_c0:
	lw $t2, 8($t0)

	bne $0, $t2, push_front_deque_c1
	sw $t1, 8($t0)
push_front_deque_c1:
	sw $t1, 4($t0)

	jr $ra
	
peek_back_deque:
	lw $t0, 8($a0)
	lw $v0, 8($t0)

	jr $ra

peek_front_deque:
	lw $t0, 4($a0)
	lw $v0, 8($t0)

	jr $ra
	
pop_back_deque:
	move $t0, $a0
  
	lw $t1, 8($t0)

	lw $t2, ($t0)
	addi $t2, $t2, -1
	sw $t2, ($t0)

	lw $t2, ($t1)
	sw $t2, 8($t0)


	lw $t2, 8($t0)
	bne $t2, $0, c0_pop_back_deque
	sw $0, 4($t0)
	jr $ra

c0_pop_back_deque:
	sw $0, 4($t2)

	jr $ra

pop_front_deque:
	move $t0, $a0

	lw $t1, 4($t0)

	lw $t2, ($t0)
	addi $t2, $t2, -1
	sw $t2, ($t0)

	lw $t2, 4($t1)
	sw $t2, 4($t0)

	lw $t2, 4($t0)
	bne $t2, $0, c0_pop_front_deque
	sw $0, 8($t0)
	jr $ra
	
c0_pop_front_deque:
	sw $0, ($t2)

	jr $ra

size_deque:
	lw $v0, ($a0)

	jr $ra
