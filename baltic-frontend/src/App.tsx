import React from 'react';
import logo from './logo.svg';
import './App.css';

interface IProps { }

interface IBook {
    id: string,
    token: string,
    title: string
}

interface IState {
    books: IBook[],
    text: string,
    token: string
}

class App extends React.Component<IProps, IState> {

    state: IState = {
        books: [],
        text: '',
        token: localStorage.getItem('token') as string
    }

    componentDidMount(): void {
        fetch('http://localhost:8080/book/list')
            .then((resp) => {
                return resp.json();
            })
            .then((data) => {
                this.setState({
                    books: data
                })
            })
            .catch((error) => console.log(error))
    }

    handleSubmit(e: React.FormEvent<HTMLFormElement>) {
        e.preventDefault();
        if (!this.state.text.length) {
            return;
        }
        const newBook = {
            title: this.state.text,
            token: this.state.token
        };

        fetch('http://localhost:8080/book/add', {
            method: 'POST',
            body: JSON.stringify({ title: newBook.title, token: newBook.token }),
            headers: { 'Content-Type': 'application/json; charset=utf-8' }
        })
            .then((resp) => {
                return resp.json()
            })
            .then((data) => {
                this.setState(state => ({
                    books: state.books.concat(data),
                    text: '',
                }));
            })
    }

    handleChange(e: React.FormEvent<HTMLInputElement>): void {
        this.setState({ text: (e.target as HTMLInputElement).value });
    }

    _handleRemoveItem(id: string): void {
        fetch('http://localhost:8080/book/delete/' + id);
        var array = this.state.books.filter(book => book.id != id)
        this.setState({ books: array });
    }

    render() {
        return (
            <div>
                <h1>Road path</h1>
                <div className="content">
                    <form onSubmit={this.handleSubmit.bind(this)}>
                        <input
                            onChange={this.handleChange.bind(this)}
                            value={this.state.text}
                            placeholder="Book title"
                        />
                    </form>
                    <ul className="bookList">
                        {this.state.books.map((item) => (
                            <div className="bookItem">
                                <li key={item.id}>{item.title}</li>
                                <button onClick={(e) => this._handleRemoveItem(item.id)}>x</button>
                            </div>
                        ))}
                    </ul>
                </div>
            </div>
        )
    }
}

export default App;
