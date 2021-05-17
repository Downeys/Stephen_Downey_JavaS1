function InvestigatorOption({ investigator }) {
    return (
        <option value={JSON.stringify(investigator)}>{investigator.firstName} {investigator.lastName}</option>
    )
}

export default InvestigatorOption