import React from 'react';
import { Redirect } from 'react-router-dom';

function Login() {
    return (
        <div>
            <div id="reg">
                <input placeholder="username" id="ur" />
                <input placeholder="email" id="er" />
                <input placeholder="password" id="pr" />
                <button onClick={(e) => reg()}>Sign up</button>
            </div>
            <div id="login">
                <input placeholder="username" id="ul" />
                <input placeholder="password" id="pl" />
                <button onClick={(e) => login()}>Sign in</button>
            </div>
        </div>
    )

    function login() {
        let un = (document.querySelector('#ul') as HTMLInputElement).value
        var pd = (document.querySelector('#pl') as HTMLInputElement).value

        var data = http<AuthDto>(
            new Request("http://localhost:8080/auth/login", {
                method: 'POST',
                body: JSON.stringify({ username: un, password: pd }),
                headers: { 'Content-Type': 'application/json; charset=utf-8' }
            })
        )

        data.then((resp) => {
            if (resp.token != null) {
                localStorage.setItem("token", resp.token)
                document.location.reload()
            } else {
                alert('error')
            }
        })
    }

    function reg(): void {
        var er = (document.querySelector('#er') as HTMLInputElement).value
        let un = (document.querySelector('#ur') as HTMLInputElement).value
        var pd = (document.querySelector('#pr') as HTMLInputElement).value

        var data = http<AuthDto>(
            new Request("http://localhost:8080/auth/reg", {
                method: 'POST',
                body: JSON.stringify({ username: un, password: pd, email: er}),
                headers: { 'Content-Type': 'application/json; charset=utf-8' }
            })
        )

        data.then((resp) => {
            if (resp.token != null) {
                localStorage.setItem("token", resp.token)
                document.location.reload()
            } else {
                alert('error')
            }
        })

    }

    interface AuthDto {
        token: string,
        sk: string,
        expire: number
    }

    async function http<T>(
        request: RequestInfo
    ): Promise<T> {
        const response = await fetch(request);
        const body = await response.json();
        return body;
    }
}

export default Login;