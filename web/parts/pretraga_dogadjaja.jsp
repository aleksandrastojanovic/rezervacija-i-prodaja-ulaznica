<%-- 
    Document   : pretraga_dogadjaja
    Created on : Oct 15, 2020, 6:58:29 PM
    Author     : iq skola
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div class="container-fluid p-1 my-3 border ">

    <!--    <form class="form-inline">
            <div class="form-group">
                <label class="sr-only" for="exampleInputEmail3">Email address</label>
                <input type="email" class="form-control" id="exampleInputEmail3" placeholder="Email">
            </div>
            <div class="form-group">
                <label class="sr-only" for="exampleInputPassword3">Password</label>
                <input type="password" class="form-control" id="exampleInputPassword3" placeholder="Password">
            </div>
            <div class="checkbox">
                <label>
                    <input type="checkbox"> Remember me
                </label>
            </div>
            <button type="submit" class="btn btn-default">Sign in</button>
        </form>-->

    <form class="form-inline justify-content-center " action="pretragaDogadjaja" method="post">

        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <label class="input-group-text" for='naziv' id="naziv_labela">Pretrazi po nazivu:</label>
            </div>
            <input class="form-control" type="text" id="naziv" name='naziv' placeholder='Unesite naziv' aria-describedby="naziv_labela"><br>
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <label class="input-group-text" for="vreme_od" id="vreme_od_labela">Pretrazi po datumu od:</label>
            </div>
            <input class="form-control" type="datetime-local" id="vreme_od" name="vreme_od" placeholder="Unesite od kog datuma"  aria-describedby="vreme_od_labela" value="2001-01-01T00:00">
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <label  class="input-group-text" for='vreme_do' id="vreme_do_labela"> do:</label>
            </div>
            <input class="form-control" type="datetime-local" id="vreme_do" name='vreme_do' placeholder='Unesite do kog datuma'  aria-describedby="vreme_do_labela" value="2001-01-01T00:00">
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <label class="input-group-text" for='mesto' id="mesto_labela">Pretrazi po mestu odrzavanja:</label>
            </div>
            <input class="form-control" type="text" id="po_mestu" name='mesto' placeholder='Unesite mesto odrzavanja'  aria-describedby="mesto_labela">
        </div>
        <div class="input-group mb-3">
            <!--<div class="input-group-prepend">-->
            <input class="form-control btn btn-primary" type="submit" value="Pretrazi">
            <!--</div>-->
        </div>
    </form>


    <!--    <form>
            <div class="form-row align-items-center">
                <div class="col-auto">
                    <label class="sr-only" for="inlineFormInput">Name</label>
                    <input type="text" class="form-control mb-2" id="inlineFormInput" placeholder="Jane Doe">
                </div>
                <div class="col-auto">
                    <label class="sr-only" for="inlineFormInputGroup">Username</label>
                    <div class="input-group mb-2">
                        <div class="input-group-prepend">
                            <div class="input-group-text">@</div>
                        </div>
                        <input type="text" class="form-control" id="inlineFormInputGroup" placeholder="Username">
                    </div>
                </div>
                <div class="col-auto">
                    <div class="form-check mb-2">
                        <input class="form-check-input" type="checkbox" id="autoSizingCheck">
                        <label class="form-check-label" for="autoSizingCheck">
                            Remember me
                        </label>
                    </div>
                </div>
                <div class="col-auto">
                    <button type="submit" class="btn btn-primary mb-2">Submit</button>
                </div>
            </div>
        </form>-->
</div>

