export default function(): string {
    const selection = window.getSelection()
    return selection ? selection.toString(): ""
}
