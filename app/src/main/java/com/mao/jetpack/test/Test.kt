package com.mao.jetpack.test

import java.lang.IllegalArgumentException

/**
 *
 * @author zhangkun
 * @time 2022/3/17 17:41
 * @Description
 */
class Test {


    lateinit var university: University1<Student>
    var university11: University1<Student> = University1<FemaleStudent>("xxx")


    fun test() {

        university = University1<FemaleStudent>("xxx")

        val get: FemaleStudent? = university11.get() as FemaleStudent?

    }


    var university2: University2<FemaleStudent> = University2<Student>("xxx")

    fun test2() {

        val student = FemaleStudent()

        university2.put(student)

        val student1 = Student()

        university2.put(student1 as FemaleStudent)


        val student2: Student = FemaleStudent()

        university2.put(student2 as FemaleStudent)


    }


    // 使用处协变
    fun useSiteCovariant(university: University3<out Student>) {
        val femaleStudent: Student? = university.get()


        val femaleStudent2: FemaleStudent? = university.get() as FemaleStudent?

        // 报错: Require Nothing? found Student?
        // university.put(femaleStudent)
    }

    fun useSiteContravariant(university: University3<in FemaleStudent>) {

        university.put(FemaleStudent())

        university.put(Student() as FemaleStudent)

    }

    fun test3() {

        val university3: University3<Student> = University3("xx")
        val university4: University3<FemaleStudent> = University3("xx")

        useSiteCovariant(university3)
        useSiteCovariant(university4)


        useSiteContravariant(university3)

        useSiteContravariant(university4)

    }

    fun testss() {
        val str = "ssss"
        str.log()

        str.isNullOrBlank
    }


    fun tesstt(): Int {
        return 1
    }


    fun login(user: String, password: String, illegalStr: String) {

        fun validate(value:String){
            if(value.isEmpty()) {
                throw IllegalArgumentException(illegalStr)
            }
        }

        validate(user)
        validate(password)

    }

    fun login1(user: String, password: String, illegalStr: String) {

        require(user.isNotEmpty()){illegalStr}
        require(password.isNotEmpty()){illegalStr}

    }


}

fun main(){
    val str = "ssss"
    str.log()

    if (str.isNullOrBlank){
        println("为空")
    }else{
        println("不为空")
    }

   /* var student = Student()

    (student as? FemaleStudent)?.tryTest()
    (student as FemaleStudent?)?.tryTest()
    (student as? FemaleStudent?)?.tryTest()

    Utils2.U2.test()*/


    val ssss = "\t"
    ssss.log()
    println("12345$ssss")


    val t = "        |Hi Maoyan! \n" +
            "        |My name is kotlin."

    val text = """
             |Hi Maoyan! 
             |My name is kotlin. 
    """.trimMargin()

    text.log()


    val text1 = """
     👇
      |Hi world!
    |My name is kotlin.
""".trimMargin()

    println(text1)

    val list = mutableListOf<String>("maoyan1","maoyan1","maoyan5","maoyan3","maoyan4")

    val lsit = list.filter {!it.contains("maoyan3") }
    println(lsit)

    fill(person = Person("xxx"))


    val arrayout = Array<Person>(10){
        i -> Person("arr $i")
    }
    val arrayin = arrayOfNulls<Person>(10)

    copy(arrayout,arrayin)

}


fun fill(array: Array<in Person> = Array(1){i -> Person(i.toString()) }, person: Person) {

    array[0] = person

    println(array[0] is Any)
}

fun copy(arrayout: Array<out Person>,arrayIn:Array<in Person>){
    arrayIn.indices.forEach { i ->
        arrayIn[i] = arrayout[i]
    }

    println(arrayIn.toList())
}


fun String.log() {
    println(this)
}


val String?.isNullOrBlank: Boolean
    get() = this == null || this.isBlank()


open class MM1{
    internal open fun test(){}
}

class MM2 : MM1() {

     public override fun test() {
        super.test()
    }

}

class MM3{
    val sss = "222";
    inner class MM4{

        fun teett(){

            sss.log()
        }
    }
}